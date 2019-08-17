package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.*;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.PreconditionsRest;
import com.viictrp.api.finance.server.api.converter.lancamento.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.FaturaDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.lancamento.LancamentoRepository;
import org.bson.types.ObjectId;
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

    public LancamentoService(LancamentoRepository repository,
                             LancamentoConverter converter,
                             IFaturaService faturaService,
                             ICarteiraService carteiraService,
                             IUsuarioService usuarioService,
                             ICategoriaService categoriaService) {
        this.repository = repository;
        this.converter = converter;
        this.faturaService = faturaService;
        this.carteiraService = carteiraService;
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }

    @Override
    public LancamentoDTO salvar(ObjectId id, LancamentoDTO lancamentoDTO, OAuthUser user, Class clazz) {
        validar(lancamentoDTO);
        Lancamento lancamento = converter.toEntity(lancamentoDTO);
        ObjectId categoriaId = new ObjectId(lancamentoDTO.getCategoriaId());
        categoriaService.buscarPorId(categoriaId, user);
        lancamento.setCategoriaId(categoriaId);
        Audity.audityEntity(user, lancamento);
        repository.save(lancamento);
        return converter.toDto(lancamento);
    }

    @Override
    public LancamentoDTO buscarLancamento(ObjectId id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("O lançamento não foi encontrado para o ID fornecido"));
    }

    @Override
    public List<LancamentoDTO> buscarLancamentosByFatura(ObjectId idFatura) {
        FaturaDTO fatura = faturaService.buscarFatura(idFatura);
        return toLancamentoDTOList(repository.findByFaturaId(new ObjectId(fatura.getId())));
    }

    @Override
    public List<LancamentoDTO> buscarLancamentosByCarteira(ObjectId idCarteira, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = carteiraService.buscarCarteira(idCarteira, usuario);
        return toLancamentoDTOList(repository.findByCarteiraId(carteira.getId()));
    }

    private List<LancamentoDTO> toLancamentoDTOList(List<Lancamento> lancamentos) {
        return lancamentos.stream().map(converter::toDto).collect(Collectors.toList());
    }

    private void validar(LancamentoDTO lancamento) {
        PreconditionsRest.checkCondition(
                lancamento.getCategoriaId() != null,
                "Por favor informe o código da fatura");
        PreconditionsRest.checkCondition(
                lancamento.getFaturaId() != null || lancamento.getCarteiraId() != null,
                "Por favor informe se o lançamento é de fatura ou carteira");
    }

}
