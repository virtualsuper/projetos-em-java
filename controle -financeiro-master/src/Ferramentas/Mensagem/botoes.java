/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas.Mensagem;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author ANDRE PORTO
 */
public interface botoes {

    /**
     *
     */
    Image aa = Toolkit.getDefaultToolkit().createImage(botoes.class.getResource("/imagens/icone.png"));
    ImageIcon fundoArtigo = new ImageIcon(botoes.class.getResource("/imagens/artigo/fundoArtigo.png"));

    ImageIcon btSair = new ImageIcon(botoes.class.getResource("/imagens/botoes/sair.png"));
    ImageIcon btEntrar = new ImageIcon(botoes.class.getResource("/imagens/botoes/entrar.png"));

    ImageIcon btBuscar = new ImageIcon(botoes.class.getResource("/imagens/botoes/buscar.png"));
    ImageIcon btTransferir = new ImageIcon(botoes.class.getResource("/imagens/botoes/transferir.png"));
    ImageIcon btVisualizar = new ImageIcon(botoes.class.getResource("/imagens/botoes/visualizar.png"));

    //principal
    ImageIcon btSefa = new ImageIcon(botoes.class.getResource("/imagens/botoes/sefa.png"));
    ImageIcon btIcms = new ImageIcon(botoes.class.getResource("/imagens/botoes/icms.png"));
    ImageIcon btNcm = new ImageIcon(botoes.class.getResource("/imagens/botoes/ncm.png"));
    ImageIcon btPis = new ImageIcon(botoes.class.getResource("/imagens/botoes/pis.png"));
    ImageIcon btCritica = new ImageIcon(botoes.class.getResource("/imagens/botoes/critica.png"));
    ImageIcon btSolicitacao = new ImageIcon(botoes.class.getResource("/imagens/botoes/solicitacao.png"));
    ImageIcon btSolConsulta = new ImageIcon(botoes.class.getResource("/imagens/botoes/solConsulta.png"));
    ImageIcon btCadastro = new ImageIcon(botoes.class.getResource("/imagens/botoes/cadastro.png"));
    ImageIcon btDanfe = new ImageIcon(botoes.class.getResource("/imagens/botoes/danfe.png"));
    ImageIcon btArtigo = new ImageIcon(botoes.class.getResource("/imagens/botoes/artigo.png"));

    ImageIcon btGrupoConsulta = new ImageIcon(botoes.class.getResource("/imagens/botoes/grupoConsulta.png"));
    ImageIcon btMais = new ImageIcon(botoes.class.getResource("/imagens/botoes/mais.png"));
    ImageIcon btXls = new ImageIcon(botoes.class.getResource("/imagens/botoes/xls.png"));
    ImageIcon btConsulta = new ImageIcon(botoes.class.getResource("/imagens/botoes/consulta.png"));
    ImageIcon btPesquisar = new ImageIcon(botoes.class.getResource("/imagens/botoes/pesquisar.png"));
    ImageIcon btSalvar = new ImageIcon(botoes.class.getResource("/imagens/botoes/salvar.png"));
    ImageIcon btCancelar = new ImageIcon(botoes.class.getResource("/imagens/botoes/cancelar.png"));
    ImageIcon btExcluir = new ImageIcon(botoes.class.getResource("/imagens/botoes/excluir.png"));
    ImageIcon btAtualizar = new ImageIcon(botoes.class.getResource("/imagens/botoes/atualizar.png"));
    ImageIcon btImprimir = new ImageIcon(botoes.class.getResource("/imagens/botoes/imprimir.png"));
    ImageIcon btImportarXML = new ImageIcon(botoes.class.getResource("/imagens/botoes/importarXML.png"));
    ImageIcon btImportar = new ImageIcon(botoes.class.getResource("/imagens/botoes/importar.png"));
    ImageIcon btTransferirArquivo = new ImageIcon(botoes.class.getResource("/imagens/botoes/transferirArquivo.png"));

    //menus
    ImageIcon M_ANALISE_PRREVIA = new ImageIcon(botoes.class.getResource("/imagens/menus/analisePrevia.png"));
    ImageIcon M_LEGISLACAO = new ImageIcon(botoes.class.getResource("/imagens/menus/legislacaoGrupos.png"));
    ImageIcon M_CST = new ImageIcon(botoes.class.getResource("/imagens/menus/verificarCst.png"));
    ImageIcon M_CADASTRA_USUARIO = new ImageIcon(botoes.class.getResource("/imagens/menus/cadastrarUsuarios.png"));
    ImageIcon M_SOLICITACAO = new ImageIcon(botoes.class.getResource("/imagens/menus/solicitacao.png"));
    ImageIcon M_VERIFICAR_GTIN = new ImageIcon(botoes.class.getResource("/imagens/menus/verificarGtin.png"));
    ImageIcon M_ESCREVER_ARTIGO = new ImageIcon(botoes.class.getResource("/imagens/menus/escreverArtigo.png"));
    ImageIcon M_USUARIO_LOGADO = new ImageIcon(botoes.class.getResource("/imagens/menus/usuariosLogados.png"));

}
