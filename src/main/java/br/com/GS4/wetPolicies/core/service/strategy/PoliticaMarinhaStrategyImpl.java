package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoliticaMarinhaStrategyImpl implements PoliticaMarinhaStrategy {

    private static final List<String> MARINHA_KEYWORDS = Arrays.asList(
            "oceano", "mar", "rio", "lago", "água", "peixe", "tubarão", "baleia", "golfinho", "tartaruga",
            "coral", "recife", "poluição", "plástico", "microplástico", "pesca", "aquicultura", "navio",
            "navegação", "porto", "costa", "maré", "corrente", "onda", "resíduo", "água doce", "água salgada",
            "zona costeira", "biodiversidade marinha", "ecossistema aquático", "pescador", "fauna marinha",
            "flora marinha", "manguezal", "área marinha protegida", "aquecimento global", "acidificação dos oceanos"
    );


    @Override
    public List<DeputadoDto> filtrarDeputadosPoliticaMarinha(List<DeputadoDto> deputados) {
        return deputados.stream()
                .filter(deputado -> possuiTermoMarinho(deputado.nome()) ||
                        possuiTermoMarinho(deputado.siglaPartido()) ||
                        possuiTermoMarinho(deputado.siglaUf()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProposicaoDto> filtrarProposicoesPoliticaMarinha(List<ProposicaoDto> proposicoes) {
        return proposicoes.stream()
                .filter(proposicao ->
                        possuiTermoMarinho( proposicao.ementa() )
                ).toList();
    }

    @Override
    public List<VotacaoDto> filtrarVotacoesPoliticaMarinha(List<VotacaoDto> votacoes) {
        return votacoes.stream()
                .filter(votacao ->
                                (possuiTermoMarinho(votacao.id().toString()) ||
                                        possuiTermoMarinho(votacao.id().toString()) ||
                                        possuiTermoMarinho(votacao.idOrgao().toString())))
                .collect(Collectors.toList());
    }

    private boolean possuiTermoMarinho(String texto) {
        for (String keyword : MARINHA_KEYWORDS) {
            if (texto != null && texto.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}