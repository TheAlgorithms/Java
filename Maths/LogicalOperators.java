// Operadores lógicos.
import javax.swing.*;

public class LogicalOperators {

	public static void main( String args[] )
	{

		// crear JTextArea para mostrar los resutados
		JTextArea exitArea = new JTextArea( 17, 20 );

		// adjuntar JTextArea a un JScrollPane, para desplazarse por los resultados
		JScrollPane displacer = new JScrollPane( exitArea );

		// crear la tabla de verdad para el operador && (AND condicional)
		String exitAux = "AND condicional (&&)" +
			"\nfalse && false: " + (false && false) +
			"\nfalse && true: " + (false && true) +
			"\ntrue && false: " + (true && false) +
			"\ntrue && true: " + (true && true);

		// crear la tabla de verdad para el operador || (OR condicional)
		exitAux += "\n\nOR condicional (||)" +
			"\nfalse || false: " + (false || false) +
			"\nfalse || true: " + (false || true) +
			"\ntrue || false: " + (true || false) +
			"\ntrue || true: " + (true || true);

		// crear la tabla de verdad para el operador & (AND lógico booleano)
		exitAux += "\n\nAND l\u00F3gico booleano (&)" +
			"\nfalse & false: " + (false & false) +
			"\nfalse & true: " + (false & true) +
			"\ntrue & false: " + (true & false) +
			"\ntrue & true: " + (true & true);

		// crear la tabla de verdad para el operador | (OR inclusivo lógico booleano)
		exitAux += "\n\nOR incluyente l\u00F3gico booleano (|)"+
			"\nfalse | false: " + (false | false) +
			"\nfalse | true: " + (false | true) +
			"\ntrue | false: " + (true | false) +
			"\ntrue | true: " + (true | true);

		// crear la tabla de verdad para el operador ^ (OR excluyente lógico booleano)
		exitAux += "\n\nOR excluyente l\u00F3gico booleano (^)" +
			"\nfalse ^ false: " + (false ^ false) +
			"\nfalse ^ true: " + (false ^ true) +
			"\ntrue ^ false: " + (true ^ false) +
			"\ntrue ^ true: " + (true ^ true);

		// crear la tabla de verdad para el operador ! (negación lógica)
		exitAux += "\n\nNOT l\u00F3gico (!)" +
			"\n!false: " + (!false) +
			"\n!true: " + (!true);

		exitArea.setText( exitAux ); // colocar los resultados en el objeto JTextArea
		JOptionPane.showMessageDialog( null, displacer,
			"Truth tables", JOptionPane.INFORMATION_MESSAGE );

		System.exit( 0 ); // terminar la aplicación

	} // fin del método main
	
} // fin de la clase OperadoresLogicos