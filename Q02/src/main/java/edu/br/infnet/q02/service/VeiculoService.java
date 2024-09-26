package edu.br.infnet.q02.service;

import edu.br.infnet.q02.model.Veiculo;
import edu.br.infnet.q02.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
       Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
       if (veiculoOptional.isEmpty()) {
           throw new RuntimeException("Veículo não encontrado! Impossivel atualizar.");
       }
        Veiculo veiculoAtualizado = veiculoOptional.get();
        veiculoAtualizado.setPlaca(veiculo.getPlaca());
        veiculoAtualizado.setMarca(veiculo.getMarca());
        veiculoAtualizado.setModelo(veiculo.getModelo());
        veiculoAtualizado.setAno(veiculo.getAno());
        return veiculoRepository.save(veiculoAtualizado);
    }

    public void excluirVeiculo(Long id) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if(veiculoOptional.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado! Impossivel excluir.");
        }
        veiculoRepository.deleteById(id);
    }


}
