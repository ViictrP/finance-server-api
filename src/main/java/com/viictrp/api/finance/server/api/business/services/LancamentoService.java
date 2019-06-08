package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.*;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.lancamento.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.*;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.lancamento.LancamentoRepository;
import com.viictrp.api.finance.server.api.strategy.LancamentoStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService implements ILancamentoService {

    private final LancamentoRepository repository;
    private final LancamentoConverter converter;
    private final IFaturaService faturaService;
    private final ICarteiraService carteiraService;
    private final IUsuarioService usuarioService;
    private final ICategoriaService categoriaService;
    private final LancamentoStrategy strategy;

    public LancamentoService(LancamentoRepository repository,
                             LancamentoConverter converter,
                             IFaturaService faturaService,
                             ICarteiraService carteiraService,
                             IUsuarioService usuarioService, ICategoriaService categoriaService, LancamentoStrategy strategy) {
        this.repository = repository;
        this.converter = converter;
        this.faturaService = faturaService;
        this.carteiraService = carteiraService;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.strategy = strategy;
    }

    @Override
    public LancamentoDTO salvar(LancamentoDTO lancamento, OAuthUser user) {
        validar(lancamento);
        Lancamento entity = converter.toEntity(lancamento);
        Categoria categoria = categoriaService.buscarCategoriaEntity(lancamento.getCategoria().getId(), user);
        categoria.addLancamento(entity);
        Audity.audityEntity(user, categoria, entity);
        ILancamentoStrategyService service = strategy.map(lancamento);
        if (lancamento.getIsParcela() && lancamento.getFatura() != null) {
            service.salvarLancamentoComParcelas(entity);
        } else {
            service.salvarLancamento(entity);
        }
        return converter.toDto(entity);
    }

    @Override
    public LancamentoDTO buscarLancamento(Long id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("O lançamento não foi encontrado para o ID fornecido"));
    }

    @Override
    public List<LancamentoDTO> buscarLancamentosByFatura(Long idFatura) {
        Fatura fatura = faturaService.buscarFaturaEntity(idFatura);
        return toLancamentoDTOList(repository.findByFatura(fatura));
    }

    @Override
    public List<LancamentoDTO> buscarLancamentosByCarteira(Long idCarteira, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = carteiraService.buscarCarteira(idCarteira, usuario);
        return toLancamentoDTOList(repository.findByCarteira(carteira));
    }

    private List<LancamentoDTO> toLancamentoDTOList(List<Lancamento> lancamentos) {
        return lancamentos.stream().map(converter::toDto).collect(Collectors.toList());
    }

    private void validar(LancamentoDTO lancamento) {
        PreconditionsRest.checkCondition(
                lancamento.getCategoria().getId() != null,
                "Por favor informe o código da fatura");
        PreconditionsRest.checkCondition(
                lancamento.getFatura() != null || lancamento.getCarteira() != null,
                "Por favor informe se o lançamento é de fatura ou carteira");
    }

}
