package br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "camaraClientNova", url = "https://dadosabertos.camara.leg.br/api/v2")
public interface CamaraClientApiNova {

    // DEPUTADOS
    @GetMapping(value="/deputados") //, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Deputado> getDeputados(@RequestParam Map<String, String> params);

    @GetMapping(value="/deputados/{id}") //, produces = MediaType.APPLICATION_JSON_VALUE)
    Deputado getDeputadoById(@PathVariable("id") Integer id);


    // PROPOSICOES
//    @RequestLine("GET /proposicoes/{params}")
//    List<Proposicao> getProposicoes(@QueryMap Map<String, String> params);
//    @GetMapping(value="/proposicoes") //, produces = MediaType.APPLICATION_JSON_VALUE)
//    List<Proposicao> getProposicoes(@RequestParam Map<String, String> params);

//    @GetMapping(value="/proposicoes/{id}") //, produces = MediaType.APPLICATION_JSON_VALUE)
//    Proposicao getProposicaoById(@PathVariable("id") Long id);


    // VOTACOES
    @GetMapping(value="/votacoes") //, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Votacao> getVotacoes(@RequestParam Map<String, String> params);

    @GetMapping(value="/votacoes/{id}") //, produces = MediaType.APPLICATION_JSON_VALUE)
    Votacao getVotacaoById(@PathVariable("id") Long id);

}
