package com.sglp.sglp_api.domain.service.strategy;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import org.springframework.stereotype.Service;

@Service
public class QuesitoStrategy implements GPTStrategy {

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
                    "1. Responda em português brasileiro;"+
                    "2. Toda a resposta precisa ser clara e objetiva;" +
                    "3. Sempre iniciar a resposta da seguinte forma:" +
                        "a. Caso a resposta seja positiva: Afirmativa a resposta.;" +
                        "b. Caso a resposta seja negativa: Negativa a resposta.;" +
                        "c. Se não for possível responder a pergunta: Prejudicada a resposta.;" +
                        "d. e/ou, caso a pergunta induzir o profissional a emitir uma opinião ou se fugir do objeto da perícia: Prejudicada a resposta.;" +
                    "4. Para casos em que perguntas induzirem a emitir opinião ou fugir do objeto da perícia, informe também o fundamento da seguinte forma:" +
                    "Com base no Art. 473, §2º do CPC: É vedado ao perito ultrapassar os limites de sua designação, bem como emitir opiniões pessoais que excedam o exame técnico ou científico do objeto da perícia.";

    public static final String FORNECA_UMA_RESPOSTA = "Forneça uma resposta com base na informação: ";

    @Override
    public String buildPrompt(ChatGPTRequest request) {
        String fieldValue = request.getMessages().get(0).getContent();
        return "Por favor, siga as instruções abaixo para fornecer a resposta:\n\n" +
                REGRAS + "\n" +
                OBSERVACOES + "\n\n" +
                FORNECA_UMA_RESPOSTA +
                fieldValue + "\n\n" +
                "Seguir modelos de resposta:\n" +
                MODELO_RESPOSTA_AFIRMATIVA + MODELO_RESPOSTA_NEGATIVA + MODELO_RESPOSTA_PREJUDICADA + MODELO_RESPOSTA_PREJUDICADA_CPC;
    }

}
