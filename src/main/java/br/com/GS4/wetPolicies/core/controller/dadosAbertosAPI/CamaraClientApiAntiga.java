package br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI;

import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "camaraClientAntiga", url = "https://www.camara.leg.br/SitCamaraWS/Proposicoes.asmx")
public interface CamaraClientApiAntiga {

    @GetMapping(value="/ObterVotacaoProposicao", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Votacao> getVotacaoPorProposicao(@RequestParam Map<String, String> params);

    @GetMapping(value="/ObterVotacaoProposicao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Votacao> getVotacaoPorProposicaoById(@PathVariable Integer id);


}
