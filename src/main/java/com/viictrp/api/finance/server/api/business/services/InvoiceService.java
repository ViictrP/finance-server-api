package com.viictrp.api.finance.server.api.business.services;

import com.viictrp.api.finance.server.api.business.interfaces.IInvoiceService;
import com.viictrp.api.finance.server.api.common.Audity;
import com.viictrp.api.finance.server.api.converter.CategoryConverter;
import com.viictrp.api.finance.server.api.converter.InvoiceConverter;
import com.viictrp.api.finance.server.api.domain.Category;
import com.viictrp.api.finance.server.api.domain.Invoice;
import com.viictrp.api.finance.server.api.dto.InvoiceDTO;
import com.viictrp.api.finance.server.api.exception.ResourceNotFoundException;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.persistence.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {

    private final InvoiceRepository repository;

    private final InvoiceConverter converter;

    private final UserService userService;

    private final CategoryService categoryService;

    public InvoiceService(InvoiceRepository repository, InvoiceConverter converter, CategoryService categoryService, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public InvoiceDTO buscarPorId(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .map(converter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fatura encontrada"));
    }

    @Override
    public InvoiceDTO save(InvoiceDTO dto, OAuthUser user) {
        Invoice invoice = converter.toEntity(dto);
        invoice.setUser(userService.buscarUsuarioPorId(user.getUserId()));
        Category category = categoryService.buscarPorId(dto.getCategory().getId());
        category.addInvoice(invoice);
        Audity.audityEntity(invoice, user);
        return Optional.of(repository.save(invoice))
                .map(converter::toDto)
                .get();
    }

    @Override
    public List<InvoiceDTO> buscarFaturas(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
