/*
 * Строим треугольник Паскаля.
 * Оно же - треугольная таблица биноминальных коэффициентов.
 * Работа с аргументами командной строки.
 */
package demo;

public class PascalTriangle_v2 {
	public static void main(String[] args) {
		// проверяем количество введённых аргументов, нам нужно только 2 или 0
		// при 0-м количестве аргументов - выводим информацию о помощи
		if(args.length == 0) putHelpInfo();
		else if(args.length > 2)	putUnknownString();
		else	{
			// анализируем переданные аргументы при запуске программы
			switch(args[0])	{
				// аргумент для задания количества строк в будущем треугольнике Паскаля
				case "-l":	{
					int size = Integer.parseInt(args[1]);
					putTrianglePascal(size);
				};
					break;
				// аргумент для выдачи небольшой формы помощи
				case "-h": putHelpInfo();
					break;
				// неизвестный аргумент
				default:
					putUnknownString();
					break;

			};
		}
	}
	
	// просто вывод строки о неверно введённой опции
	private static void putUnknownString()	{
		System.out.println("Unknown option! \"-h\" for help.");
	}
	
	// вывод информации о помощи при пользовании программой
	private static void putHelpInfo()	{
		System.out.printf("Программа для расчёта \"треугольника Паскаля\".\n");
		System.out.printf("-l <число> - количество строк в треугольнике.\n");
		System.out.printf("-h\t - вывод данной информации.\n");
	}
	
	// метод вычисления треугольника Паскаля
	private static void putTrianglePascal(int size)	{
		// нужен 2-мерный массив целочисленный
		int[][] triangle = new int[size][];
				
		// создаём "пирамиду"
		for(int i = 0; i < size; i++)	{
			triangle[i] = new int[i + 1];
			int trng_str_len = triangle[i].length;
			// заполняем 1-цами 0-вые члены строк
			triangle[i][0] = 1;
			
			// заполняем 1-цами крайние члены строк, ориентируемся по длине строки
//			triangle[i][(triangle[i].length - 1)] = 1;
			// более элегантный способ заполнить "диагональ"
			// не надо делать никаких доп. вычислений, просто ставим "1" 
			// в требуемую позицию
			triangle[i][i] = 1;
			
			// заполняем строки - горизонтальные массивы
			// в промежутке от 0-го до крайнего члена строки, не включая
			for(int j = 1; j < (trng_str_len - 1); j++)	{
				// вводим необходимую арифметику для вычисления
				// внутренне содержание строки
				triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
			}
		}
		
		// выводим содержимое контейнера triangle
		// обходим triangle по 1-му уровню, n присваивается ссылка на 
		// содержащийся массив внутри
		for(int[] n : triangle)	{
			// т.к. содержимое каждой строки - есть int,
			// обходим "внутренний" массив нашим int-сканнером
			// и выводим результат в строку с указанием необходимых параметров
			// форматирования строки
			for(int m : n)	{
				System.out.printf("%6d ", m);
			}
			// по окончании строки, перед тем, как перейти к следующей
			// переводим вывод на новую строку
			System.out.println();
		}
		
	}
}
