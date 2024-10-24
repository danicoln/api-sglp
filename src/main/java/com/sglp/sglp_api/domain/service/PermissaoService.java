//package com.sglp.sglp_api.domain.service;
//
//import com.sglp.sglp_api.domain.model.Permissao;
//import com.sglp.sglp_api.domain.repository.PermissaoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class PermissaoService {
//
//    public static final String PERMISSAO_NAO_ENCONTRADA = "Permissão não encontrada";
//
//    private final PermissaoRepository repository;
//
//    public List<Permissao> listar() {
//        return repository.findAll();
//    }
//
//    @Transactional
//    public Permissao salvar(Permissao permissao) {
//        return repository.save(permissao);
//    }
//
//    @Transactional
//    public Permissao atualizar(String permissaoId, Permissao permissao) {
//        Permissao permissaoExistente = buscarPorIdOuFalhar(permissaoId);
//        BeanUtils.copyProperties(permissao, permissaoExistente, "id");
//        return repository.save(permissaoExistente);
//
//    }
//
//    @Transactional
//    public void remover(String permissaoId) {
//        repository.deleteById(permissaoId);
//    }
//
//    private Permissao buscarPorIdOuFalhar(String permissaoId) {
//        return repository.findById(permissaoId).orElseThrow(() -> new RuntimeException(PERMISSAO_NAO_ENCONTRADA));
//    }
//
//    public Optional<Permissao> buscarPorId(String permissaoId) {
//        return repository.findById(permissaoId);
//    }
//}
