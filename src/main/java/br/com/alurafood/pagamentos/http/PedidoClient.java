package br.com.alurafood.pagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("pedidos-ms")
public interface PedidoClient {

    @PutMapping("/pedidos/{id}/pago")
    void atualizaPagamento(@PathVariable Long id);

}
