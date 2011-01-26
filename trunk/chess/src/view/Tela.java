package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import view.renders.ItemRendererRemovidas;
import view.renders.ItemRendererTabuleiro;

import model.Tabuleiro;
import control.Controle;

public class Tela extends Frame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollFundo;
	private JTable tabela;

	public Tela() {
		this.setTitle("JJChessGame");
		// this.setBounds(1024, 768, 800, 500);

		init();
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		setExtendedState(MAXIMIZED_BOTH);
	}

	private void init() {
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setMenuBar(getBarraMenu());
		panel = new JPanel(new GridBagLayout());
		// panel.setSize(1100, 600);
		TabuleiroView tabuleiro1 = new TabuleiroView(Tabuleiro.getInstance());
		tabuleiro1.setSize(600, 550);
		panel.add(tabuleiro1);

		Controle controle = new Controle(tabuleiro1.getTabuleiro(), null);
		tabuleiro1.setControle(controle);

		tabela = new JTable(PecasCapturadasTableModel.getInstance());
		tabela.setRowHeight(55);
		tabela.getColumnModel().getColumn(0).setMaxWidth(60);
		tabela.getColumnModel().getColumn(1).setMaxWidth(60);

		tabela.setDefaultRenderer(Object.class, new ItemRendererRemovidas(
				PecasCapturadasTableModel.getInstance()));
		tabela.setMaximumSize(new Dimension(120, 500));

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBorder(BorderFactory.createTitledBorder("Pe�as Capturadas"));
		scroll.setPreferredSize(new Dimension(140, 500));
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);

		scrollFundo = new JScrollPane();
		scrollFundo.setViewportView(panel);
		scrollFundo.setPreferredSize(new Dimension(1000, 600));
		add(scrollFundo);
		pack();
	}

	public MenuBar getBarraMenu() {
		MenuBar barraMenu = new MenuBar();
		Menu menuArquivo = new Menu("File");
		MenuItem itemSave = new MenuItem("Save");

		MenuItem itemLoad = new MenuItem("Open");
		
		MenuItem itemExit = new MenuItem("Exit");

		MenuItem itemAbout = new MenuItem("about");
		itemLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});

		itemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// diretorioSalvar();
			}
		});

		itemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		
		itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		barraMenu.add(menuArquivo);
		//menuArquivo.add(itemLoad);
		//menuArquivo.add(itemSalvar);
		menuArquivo.add(itemExit);
		menuArquivo.add(itemAbout);
		return barraMenu;
		
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		Tela tela = new Tela();
		tela.setVisible(true);
	}

	private void about() {
		JOptionPane equipe = new JOptionPane("About JJChessGame");
		equipe.showMessageDialog(this,
				"JJChessGame\n\nDeselvolvido por Jean Victor Zunino\n"
						+ "Prot�tipo de jogo de xadrez.", "Equipe",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
