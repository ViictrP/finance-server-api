package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IFaturaService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.InvoiceConverter;
import com.viictrp.api.finance.server.api.domain.Categoria;
import com.viictrp.api.finance.server.api.domain.Fatura;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturaService implements IFaturaService {

    private final InvoiceRepository repository;

    private final InvoiceConverter converter;

    private final UserService userService;

    private final CategoriaService categoryService;

    public FaturaService(InvoiceRepository repository, InvoiceConverter converter, CategoriaService categoryService, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public InvoiceDTO buscarPorId(Long id, Long userId) {
        return repository.findByIdAndUsuarioIdAndExcluidoIsFalse(id, userId)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fatura encontrada"));
    }

    @Override
    public InvoiceDTO save(InvoiceDTO dto, OAuthUser user) {
        Fatura fatura = converter.toEntity(dto);
        fatura.setUsuario(userService.buscarUsuarioPorId(user.getUsuarioId()));
        Categoria categoria = categoryService.buscarPorId(dto.getCategory().getId(), user);
        categoria.addInvoice(fatura);
        Audity.audityEntity(fatura, user);
        repository.save(fatura);
        return converter.toDto(fatura);
    }

    @Override
    public InvoiceDTO atualizarInvoice(InvoiceDTO dto, OAuthUser user) {
        Fatura faturaBanco = repository.findByIdAndUsuarioIdAndExcluidoIsFalse(dto.getId(), user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Fatura not found for the ID."));
        Fatura fatura = converter.toEntity(dto);
        fatura.setCategoria(categoryService.buscarPorId(dto.getCategory().getId(), user));
        faturaBanco.mergeDados(fatura);
        repository.save(faturaBanco);
        return converter.toDto(faturaBanco);
    }

    @Override
    public List<InvoiceDTO> buscarFaturas(Long userId) {
        return repository.findByUsuarioIdAndExcluidoIsFalse(userId)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void apagarInvoice(Long id, OAuthUser user) {
        Fatura faturaBanco = repository.findByIdAndUsuarioIdAndExcluidoIsFalse(id, user.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Fatura not found for the ID."));
        faturaBanco.setExcluido(true);
        repository.save(faturaBanco);
    }
}
