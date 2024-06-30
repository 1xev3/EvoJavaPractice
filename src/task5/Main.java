package task5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//💡- Ввести с консоли дату в формата: 31.12.2020, сохранить ее в переменной класса Date (преобразовав введенную строку с использованием SimpleDateFormat)
//
//💡- Увеличить дату на 45 дней и вывести на экран
//
//💡- Сдвинуть дату на начало года и вывести на экран
//
//💡- Увеличить дату на 10 рабочих дней (считаем субботы и воскресенья выходными) и вывести на экран
//
//💡- Ввести с консоли вторую дату в том же формате и сохранить ее в другой переменной класса Date
//
//💡- Посчитать количество рабочих дней (субботы и воскресенья - выходные) между первой и второй датами введенными с консоли и вывести на экран
//
//Пример:
//Вывод: Введите дату в формате dd.MM.yyyy:
//Ввод: 10.01.2024
//Вывод: Дата после увеличения на 45 дней: 24.02.2024
//Вывод: Дата после сдвига на начало года: 01.01.2024
//Вывод: Дата после увеличения на 10 рабочих дней: 24.01.2024
//Вывод: Введите вторую дату в формате dd.MM.yyyy:
//Ввод: 01.01.2024
//Вывод: Количество рабочих дней между введенными датами: 7 (если не включать последнюю дату)

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {

            System.out.print("Enter date with format 'dd.MM.yyyy'\n> ");
            String dateString = scanner.nextLine();
            Date date = dateFormat.parse(dateString);

            // Добавить 45 дней
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 45);
            System.out.println("Your date + 45 days: " + dateFormat.format(calendar.getTime()));

            // Сдвинуть дату на начало года и вывести на экран
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            System.out.println("Your date after shift to year first day: " + dateFormat.format(calendar.getTime()));

            // Увеличить дату на 10 рабочих дней (считаем субботы и воскресенья выходными) и вывести на экран
            calendar.setTime(date);
            int workDaysAdded = 0;
            while (workDaysAdded < 10) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                        && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    workDaysAdded++;
                }
            }
            System.out.println("Your date + 10 days (not counting weekend): " + dateFormat.format(calendar.getTime()));

            // Ввести с консоли вторую дату в том же формате и сохранить ее в другой переменной класса Date
            System.out.print("Введите вторую дату в формате dd.MM.yyyy: ");
            String secondDateString = scanner.nextLine();
            Date secondDate = dateFormat.parse(secondDateString);

            // Подсчет количества рабочих дней между двумя датами
            long workDaysCount = countWorkDays(date, secondDate);
            System.out.println("Количество рабочих дней между введенными датами: " + workDaysCount);

        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Пожалуйста, используйте формат dd.MM.yyyy.");
        }
    }

    private static long countWorkDays(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        long workDays = 0;

        // Если начальная дата после конечной, поменяем их местами
        if (startCal.after(endCal)) {
            Calendar temp = startCal;
            startCal = endCal;
            endCal = temp;
        }

        while (startCal.before(endCal) || startCal.equals(endCal)) { // включаем последнюю тоже
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workDays++;
            }
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }

        return workDays;
    }
}
