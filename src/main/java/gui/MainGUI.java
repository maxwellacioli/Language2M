package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import analyzer.Analyzer2M;
import lexical.LexicalAnalyzer;

public class MainGUI extends JFrame implements ActionListener {
	private JFrame mainFrame;

	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JMenuBar menuBar;

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu runMenu;

	private JMenuItem runFileMenuItem;

	private JMenuItem newFileMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem saveFileMenuItem;

	private JMenuItem cutMenuItem;
	private JMenuItem CopyMenuItem;
	private JMenuItem pasteMenuItem;

	private Analyzer2M analyzer2m;

	public MainGUI(Analyzer2M analyzer2m) {
		super("LANGUAGE 2M");
		this.analyzer2m = analyzer2m;

		mainFrame = new JFrame("Editor");

		try {
			// Set metl look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());

		} catch (Exception e) {
		}

		initComponents();

		mainFrame.setJMenuBar(menuBar);
		mainFrame.setSize(1200, 600);
		mainFrame.add(scrollPane);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void textContent() {
		textArea.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				textChanged();
			}

			public void insertUpdate(DocumentEvent e) {
				textChanged();
			}

			public void changedUpdate(DocumentEvent e) {
				textChanged();
			}
		});
	}

	private void textChanged() {
		if (textArea.getText().equals("")) {
			runFileMenuItem.setEnabled(false);
		} else {
			runFileMenuItem.setEnabled(true);
		}
	}

	private void initComponents() {
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textContent();

		menuBar = new JMenuBar();

		fileMenu = new JMenu("Arquivo");
		newFileMenuItem = new JMenuItem("Novo");
		openFileMenuItem = new JMenuItem("Abrir");
		saveFileMenuItem = new JMenuItem("Salvar");
		newFileMenuItem.addActionListener(this);
		openFileMenuItem.addActionListener(this);
		saveFileMenuItem.addActionListener(this);
		fileMenu.add(newFileMenuItem);
		fileMenu.add(openFileMenuItem);
		fileMenu.add(saveFileMenuItem);

		menuBar.add(fileMenu);

		editMenu = new JMenu("Editar");
		cutMenuItem = new JMenuItem("Cortar");
		CopyMenuItem = new JMenuItem("Copiar");
		pasteMenuItem = new JMenuItem("Colar");
		cutMenuItem.addActionListener(this);
		CopyMenuItem.addActionListener(this);
		pasteMenuItem.addActionListener(this);
		editMenu.add(cutMenuItem);
		editMenu.add(CopyMenuItem);
		editMenu.add(pasteMenuItem);

		menuBar.add(editMenu);

		runMenu = new JMenu("Programa");
		runFileMenuItem = new JMenuItem("Executar");
		runFileMenuItem.setEnabled(false);
		runFileMenuItem.addActionListener(this);
		runMenu.add(runFileMenuItem);

		menuBar.add(runMenu);

	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		if (s.equals("Cortar")) {
			textArea.cut();
		} else if (s.equals("Copiar")) {
			textArea.copy();
		} else if (s.equals("Colar")) {
			textArea.paste();
		} else if (s.equals("Salvar")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("mainFrame:");

			// Invoke the showsSaveDialog function to show the save dialog
			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// Create a file writer
					FileWriter wr = new FileWriter(fi, false);

					// Create buffered writer to write
					BufferedWriter w = new BufferedWriter(wr);

					// Write
					w.write(textArea.getText());

					w.flush();
					w.close();
				} catch (Exception evt) {
					JOptionPane.showMessageDialog(mainFrame, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(mainFrame, "Operação Cancelada!");
		} else if (s.equals("Abrir")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("mainFrame:");

			// Invoke the showsOpenDialog function to show the save dialog
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if (r == JFileChooser.APPROVE_OPTION) {
				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					// Initilize sl
					sl = br.readLine();

					// Take the input from the file
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					// Set the text
					textArea.setText(sl);
				} catch (Exception evt) {
					JOptionPane.showMessageDialog(mainFrame, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(mainFrame, "Operação Cancelada!");
		} else if (s.equals("Executar")) {
			LexicalAnalyzer.getInstance().readFile(getSourceCode());
			analyzer2m.start();
			System.exit(1);
		} else if (s.equals("Novo")) {
			textArea.setText("");
		}
	}

	public List<String> getSourceCode() {
		String lines[] = textArea.getText().split("\\r?\\n");
		List<String> linesList = new ArrayList<String>(Arrays.asList(lines));

		return linesList;
	}

	public static void main(String[] args) {

		Analyzer2M analyzer2m = new Analyzer2M();
		
		if(args.length != 0) {
			File text = new File(args[0]);
		      
	        //Creating Scanner instnace to read File in Java
	        Scanner scnr = null;
			try {
				scnr = new Scanner(text);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	      
	        //Reading each line of file using Scanner class
			List<String> linesList = new ArrayList<String>();
	        while(scnr.hasNextLine()){
	            String line = scnr.nextLine();
	            linesList.add(line);
	        }
	        LexicalAnalyzer.getInstance().readFile(linesList);
			analyzer2m.start();

		} else {
			new MainGUI(analyzer2m);
		}
	}
}
