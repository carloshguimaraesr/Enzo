package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return service.criarPedido(pedido);
    }

    @GetMapping("/abertos")
    public List<Pedido> listarPedidosAbertos() {
        return service.listarPedidosAbertos();
    }

    @PatchMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestParam Pedido.StatusPedido status) {
        return service.atualizarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        service.excluirPedido(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        return service.atualizarPedido(id, pedidoAtualizado);
    }


}
