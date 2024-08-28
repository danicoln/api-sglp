package com.sglp.sglp_api.domain.service.strategy;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import com.sglp.sglp_api.domain.exception.QuebraDeRegraException;
import com.sglp.sglp_api.domain.model.LaudoPericial;
import com.sglp.sglp_api.domain.service.LaudoPericialService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class QuesitoStrategy implements GPTStrategy {

    public static final String OBJETIVO_NULL_OU_VAZIO = "O objetivo do laudo não pode estar vazio.";
    private final LaudoPericialService laudoService;
    private String objetivoDoLaudo;

    public static final String MODELO_RESPOSTA_AFIRMATIVA = "Afirmativa a resposta. Justificativa exemplo da resposta à pergunta.";
    public static final String MODELO_RESPOSTA_NEGATIVA = "Negativa a resposta. Justificativa exemplo da resposta à pergunta.";
    public static final String MODELO_RESPOSTA_PREJUDICADA = "Prejudicada a resposta. Justificativa exemplo da resposta à pergunta.";
    public static final String MODELO_RESPOSTA_PREJUDICADA_CPC = "Prejudicada a resposta. Justificativa exemplo da resposta à pergunta. " +
            "Com base no Art. 473, §2º do CPC: É vedado ao perito ultrapassar os limites de sua designação, bem como emitir opiniões pessoais que excedam o exame técnico ou científico do objeto da perícia.";

    public static final String OBSERVACOES =
            "Observações:" +
                    "1. Sempre confeccionar os textos na terceira pessoa do singular;" +
                    "2. Ser extritamente técnico, não podendo emitir opinião;";

    public static final String REGRAS =
            "Regras:" +
                    "1. Responda em português brasileiro;" +
                    "2. Toda a resposta precisa ser clara e objetiva;" +
                    "3. Sempre iniciar a resposta da seguinte forma:" +
                    "a. Caso a resposta seja positiva: Afirmativa a resposta.;" +
                    "b. Caso a resposta seja negativa: Negativa a resposta.;" +
                    "c. Se não for possível responder a pergunta: Prejudicada a resposta.;" +
                    "d. e/ou, caso a pergunta induzir o profissional a emitir uma opinião ou se fugir do objeto da perícia: Prejudicada a resposta.;" +
                    "4. Para casos de emissão de opinião ou fugir do objeto da perícia, justifique-a e descreva em seguida o Art. 473 da seguinte forma:" +
                    "Com base no Art. 473, §2º do CPC: É vedado ao perito ultrapassar os limites de sua designação, bem como emitir opiniões pessoais que excedam o exame técnico ou científico do objeto da perícia.";

    public static final String FORNECA_UMA_RESPOSTA = "Forneça uma resposta com base na informação: ";

    public QuesitoStrategy(LaudoPericialService laudoService) {
        this.laudoService = laudoService;
    }

    @Override
    public String buildPrompt(ChatGPTRequest request) {
        if(objetivoDoLaudo == null || objetivoDoLaudo.isEmpty()){
            throw new QuebraDeRegraException(OBJETIVO_NULL_OU_VAZIO);
        }
        String fieldValue = request.getMessages().get(0).getContent();
        return new StringBuilder()
                .append("Por favor, siga as instruções abaixo para fornecer a resposta:\n\n")
                .append(REGRAS).append("\n")
                .append(OBSERVACOES).append("\n")
                .append("Objetivo do laudo: ").append(objetivoDoLaudo).append("\n\n")
                .append(FORNECA_UMA_RESPOSTA)
                .append(fieldValue).append("\n\n")
                .append("Seguir modelos de resposta:\n")
                .append(MODELO_RESPOSTA_AFIRMATIVA).append("\n")
                .append(MODELO_RESPOSTA_NEGATIVA).append("\n")
                .append(MODELO_RESPOSTA_PREJUDICADA).append("\n")
                .append(MODELO_RESPOSTA_PREJUDICADA_CPC)
                .toString();
    }

    public void validarObjetivo(String laudoId) {
        LaudoPericial laudoPericial = laudoService.buscarPorIdOuFalhar(laudoId);
        this.objetivoDoLaudo = laudoPericial.getObjetivo();
    }
}
