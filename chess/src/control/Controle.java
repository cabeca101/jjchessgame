package control;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Peca;
import model.Tabuleiro;

public class Controle {

	/*
	 Quando uma pe�a Branca Joga:
	 *Verificar se o Rei branco n�o est� check( Se tiver Volta jogada e da mensagem )
	 *Verificar se o Rei Preto est� em check( Da mensagem Check ).
	 */
	private Peca pecaSelecionada1 = null;
	private Peca pecaSelecionada2 = null;
	private Tabuleiro tabuleiro = null;
	
	public Controle(Tabuleiro tabuleiro1, Tabuleiro tabuleiro2) {
		// this.tabuleiro1 = tabuleiro1;
		// this.tabuleiro2 = tabuleiro2;
		// TODO Auto-generated constructor stub
	}

	public void subJogar(MouseEvent evento, int selectedRow, int selectedColumn) {
		try {
			jogar(evento, selectedRow, selectedColumn);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
			pecaSelecionada1 = null;
			pecaSelecionada2 = null;
		}
	}

	private boolean jogar(MouseEvent e, int linha, int coluna) throws Exception {
		try {
			JTable tabela = (JTable) e.getSource();
			tabuleiro = (Tabuleiro) tabela.getModel();
			if (pecaSelecionada1 == null && pecaSelecionada2 == null) {
				// Coloca a pe�a selecionada em pecaSelecionada1
				pecaSelecionada1 = tabuleiro.getPeca(linha, coluna);
				if (pecaSelecionada1 != null)
					// Verifica se a pe�a selecionada � diferente da pe�a da vez
					if (!pecaSelecionada1.getCor().equals(
							tabuleiro.getJogadorVez())) {
						throw new IllegalArgumentException("N�o � sua vez");
					}
			} else {
				pecaSelecionada2 = tabuleiro.getPeca(linha, coluna);
				if (pecaSelecionada2 != null) {
					if (pecaSelecionada2.getCor().equals(
							pecaSelecionada1.getCor())) {
						if (pecaSelecionada1 != pecaSelecionada2) {
							// JOptionPane.showMessageDialog(null,"Voc� deve jogar com a pe�a que tocou primeiro!");
						}
						pecaSelecionada2 = null;
						return false;
					} else {
						try {
							Peca pecaCapturada = pecaSelecionada1
									.capturar(pecaSelecionada2);
						} catch (Exception e2) {
							throw new IllegalArgumentException(e2.getMessage());
						}
					}
				} else {
					if (pecaSelecionada1.verificaDest(linha, coluna)) {
						pecaSelecionada1.movimentar(linha, coluna);
						if (pecaSelecionada1.isCheckOponente(tabuleiro.getPecas())) {
							JOptionPane.showMessageDialog(null, "Check");
						}
					} else {
						throw new IllegalArgumentException("Movimento inv�lido");
					}
				}
				tabuleiro.passaVez();
				pecaSelecionada1 = null;
				pecaSelecionada2 = null;
				return true;
			}
			return false;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

}