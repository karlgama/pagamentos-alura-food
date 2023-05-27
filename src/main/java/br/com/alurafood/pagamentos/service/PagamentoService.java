package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository repository;
    private final ModelMapper mapper;

    public Page<PagamentoDto> listar(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> mapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id) {
        var pagamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criar(PagamentoDto dto) {
        var pagamento = mapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return mapper.map(pagamento, PagamentoDto.class);
    }


    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = mapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return mapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }
}
