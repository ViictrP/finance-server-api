package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.ICarteiraService;
import com.viictrp.api.finance.server.api.business.interfaces.IUsuarioService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.common.DateUtils;
import com.viictrp.api.finance.server.api.converter.carteira.CarteiraConverter;
import com.viictrp.api.finance.server.api.converter.lancamento.LancamentoConverter;
import com.viictrp.api.finance.server.api.domain.Carteira;
import com.viictrp.api.finance.server.api.domain.Lancamento;
import com.viictrp.api.finance.server.api.domain.Orcamento;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.domain.enums.MesType;
import com.viictrp.api.finance.server.api.dto.CarteiraDTO;
import com.viictrp.api.finance.server.api.dto.LancamentoDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.security.SecurityContext;
import com.viictrp.api.finance.server.api.persistence.carteira.CarteiraRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CarteiraService implements ICarteiraService {

    private static final String CARTEIRA_NOT_FOUND = "Carteira não encontrada pra o ID fornecido";

    private final CarteiraRepository repository;
    private final IUsuarioService usuarioService;
    private final CarteiraConverter converter;
    private final LancamentoConverter lancamentoConverter;

    public CarteiraService(CarteiraRepository repository,
                           IUsuarioService usuarioService,
                           CarteiraConverter converter, LancamentoConverter lancamentoConverter) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.converter = converter;
        this.lancamentoConverter = lancamentoConverter;
    }

    @Override
    public CarteiraDTO salvar(CarteiraDTO carteiraDTO) {
        Carteira carteira = converter.toEntity(carteiraDTO);
        repository.save(carteira);
        return converter.toDto(carteira);
    }

    @Override
    public CarteiraDTO buscarCarteira(Long id, OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByIdAndUsuario(id, usuario)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public Carteira buscarCarteira(Long id, Usuario usuario) {
        return repository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
    }

    @Override
    public List<Carteira> buscarPorUsuario(OAuthUser user) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        return repository.findByUsuario(usuario);
    }

    @Override
    public void salvarOrcamentoNaCarteira(MesType mes, OAuthUser user, Orcamento orcamento) {
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        Carteira carteira = repository.findByMesAndUsuario(mes, usuario)
                .orElse(Carteira.criar(orcamento, usuario));
        orcamento.setCarteira(carteira);
        Audity.audityEntity(user, orcamento, carteira);
        repository.save(carteira);
    }

    @Override
    public LancamentoDTO salvarLancamento(Lancamento lancamento) {
        OAuthUser user = SecurityContext.getUser();
        Carteira carteira;
        MesType mes = DateUtils.getMonthName(lancamento.getData());
        Usuario usuario = usuarioService.buscarUsuario(user.getUsuarioId());
        if (DateUtils.isLastDayOfMonth()) {
            carteira = repository.findByMesAndUsuario(MesType.nextMonth(mes), usuario)
                    .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
        } else {
            carteira = repository.findByMesAndUsuario(mes, usuario)
                    .orElseThrow(() -> new ResourceNotFoundException(CARTEIRA_NOT_FOUND));
        }
        carteira.addLancamento(lancamento);
        Audity.audityEntity(user, lancamento, carteira);
        repository.save(carteira);
        return lancamentoConverter.toDto(lancamento);
    }

    @Override
    public List<LancamentoDTO> salvarLancamentoComparcelas(Lancamento lancamento) {
        return Collections.singletonList(salvarLancamento(lancamento));
    }
}
