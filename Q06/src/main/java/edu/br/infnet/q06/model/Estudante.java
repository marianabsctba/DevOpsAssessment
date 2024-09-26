package edu.br.infnet.hogwarts.model;

import edu.br.infnet.q06.util.Casa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("estudante")
public class Estudante {
    @Id
    private Long id;
    private String nome;
    private Casa casa;
}
