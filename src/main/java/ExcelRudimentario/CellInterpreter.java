package ExcelRudimentario;

import java.util.List;
import java.util.Stack;

public class CellInterpreter {
    private final List<Celda> celdas;

    public CellInterpreter(List<Celda> celdas) {
        this.celdas = celdas;
    }

    public double interpret(String input) {
        if (input.startsWith("@")) {
            return evaluateFunction(input.substring(1));
        } else if (isCellReference(input)) {
            return getCellValue(input);
        } else if (input.startsWith("+")) {
            return getCellValue(input.substring(1));
        } else if (isNumeric(input)) {
            return Double.parseDouble(input);
        } else {
            return evaluateExpression(input);
        }
    }

    private boolean isCellReference(String input) {
        return input.matches("^[A-Za-z]+\\d+$");
    }

    private double evaluateFunction(String function) {
        if (function.startsWith("sum(")) {
            String[] range = function.substring(4, function.length() - 1).split(",");
            if (range.length == 2) {
                return sumRange(range[0], range[1]);
            } else {
                throw new IllegalArgumentException("Rango de suma no válido.");
            }
        } else if (function.startsWith("max(")) {
            String[] range = function.substring(4, function.length() - 1).split(",");
            if (range.length == 2) {
                return maxRange(range[0], range[1]);
            } else {
                throw new IllegalArgumentException("Rango de máximo no válido.");
            }
        } else if (function.startsWith("min(")) {
            String[] range = function.substring(4, function.length() - 1).split(",");
            if (range.length == 2) {
                return minRange(range[0], range[1]);
            } else {
                throw new IllegalArgumentException("Rango de mínimo no válido.");
            }
        } else if (function.startsWith("avg(")) {
            String[] range = function.substring(4, function.length() - 1).split(",");
            if (range.length == 2) {
                return avgRange(range[0], range[1]);
            } else {
                throw new IllegalArgumentException("Rango de promedio no válido.");
            }
        }
        return 0.0;
    }

    private double sumRange(String start, String end) {
        char startCol = start.charAt(0);
        char endCol = end.charAt(0);
        int startRow = Integer.parseInt(start.substring(1));
        int endRow = Integer.parseInt(end.substring(1));
        double sum = 0;
        for (char col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                String cellId = col + String.valueOf(row);
                sum += getCellValue(cellId);
            }
        }
        return sum;
    }

    private double maxRange(String start, String end) {
        char startCol = start.charAt(0);
        char endCol = end.charAt(0);
        int startRow = Integer.parseInt(start.substring(1));
        int endRow = Integer.parseInt(end.substring(1));
        double max = Double.NEGATIVE_INFINITY;
        for (char col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                String cellId = col + String.valueOf(row);
                max = Math.max(max, getCellValue(cellId));
            }
        }
        return max;
    }

    private double minRange(String start, String end) {
        char startCol = start.charAt(0);
        char endCol = end.charAt(0);
        int startRow = Integer.parseInt(start.substring(1));
        int endRow = Integer.parseInt(end.substring(1));
        double min = Double.POSITIVE_INFINITY;
        for (char col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                String cellId = col + String.valueOf(row);
                min = Math.min(min, getCellValue(cellId));
            }
        }
        return min;
    }

    private double avgRange(String start, String end) {
        char startCol = start.charAt(0);
        char endCol = end.charAt(0);
        int startRow = Integer.parseInt(start.substring(1));
        int endRow = Integer.parseInt(end.substring(1));
        double sum = 0;
        int count = 0;
        for (char col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                String cellId = col + String.valueOf(row);
                sum += getCellValue(cellId);
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    private double getCellValue(String cellId) {
        for (Celda celda : celdas) {
            if (celda.getId().equals(cellId)) {
                return celda.getValue();
            }
        }
        throw new IllegalArgumentException("La celda " + cellId + " no existe.");
}


    private double evaluateExpression(String expression) {
    // Eliminar espacios en blanco de la expresión
    expression = expression.replaceAll("\\s", "");

    // Pilas para valores y operadores
    Stack<Double> values = new Stack<>();
    Stack<Character> ops = new Stack<>();

    for (int i = 0; i < expression.length(); i++) {
        char ch = expression.charAt(i);

        // Si el carácter es un dígito o una letra (referencia de celda), leer el número completo o referencia
        if (Character.isDigit(ch) || Character.isLetter(ch)) {
            StringBuilder sb = new StringBuilder();
            while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || 
                    Character.isLetter(expression.charAt(i)) || expression.charAt(i) == '.')) {
                sb.append(expression.charAt(i++));
            }
            if (isCellReference(sb.toString())) {
                values.push(getCellValue(sb.toString())); // Obtener valor de la celda
            } else {
                values.push(Double.valueOf(sb.toString())); // Agregar el número a la pila de valores
            }
            i--; // Retroceder un paso porque el bucle for también incrementa i
        } else if (ch == '(') {
            ops.push(ch); // Agregar '(' a la pila de operadores
        } else if (ch == ')') {
            while (ops.peek() != '(') {
                values.push(applyOp(ops.pop(), values.pop(), values.pop())); // Aplicar operadores dentro de los paréntesis
            }
            ops.pop(); // Descartar '('
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            while (!ops.isEmpty() && hasPrecedence(ch, ops.peek())) {
                values.push(applyOp(ops.pop(), values.pop(), values.pop())); // Aplicar operadores según la precedencia
            }
            ops.push(ch); // Agregar el operador a la pila
        }
    }

    // Aplicar el resto de los operadores a los valores
    while (!ops.isEmpty()) {
        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
    }

    // El valor en la parte superior de la pila de valores es el resultado
    return values.pop();
}


    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("División por cero");
                }
                return a / b;
        }
        return 0;
    }

    private boolean isNumeric(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
