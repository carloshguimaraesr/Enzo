package com.example.demo.service;

import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(Pedido.StatusPedido.ABERTO);
        return repository.save(pedido);
    }

    public List<Pedido> listarPedidosAbertos() {
        return repository.findByStatus(Pedido.StatusPedido.ABERTO);
    }

    public Pedido atualizarStatus(Long id, Pedido.StatusPedido novoStatus) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return repository.save(pedido);
    }

    public void excluirPedido(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        if (pedido.getStatus() == Pedido.StatusPedido.CANCELADO) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Só é possível excluir pedidos cancelados");
        }
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedidoExistente.setCliente(pedidoAtualizado.getCliente());
        pedidoExistente.setEnderecoEntrega(pedidoAtualizado.getEnderecoEntrega());
        pedidoExistente.setItens(pedidoAtualizado.getItens());
        pedidoExistente.setPlataforma(pedidoAtualizado.getPlataforma());
        pedidoExistente.setStatus(pedidoAtualizado.getStatus());
        
        return repository.save(pedidoExistente);
    }




}
