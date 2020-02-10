package pl.itacademy.schedule;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

//TODO: finish this class implementation
//TODO: use examples from project files
//TODO: generate all rows for each separate lesson
//TODO: set proper formats for date and time cells
//TODO: fill formulas for cells wit formulas
//TODO: think about how to avoid double row creation for cases, when status part of wokbook will be created
//TODO: last row with status should be next row after the last lesson, or next row after status section if we have small amount of lessons

public class ExcelGenerator {
    public Workbook createExcel(Schedule schedule) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        List<Lesson> lessons = schedule.getLessons();
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();

        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            Row row = sheet.createRow(i);
            Cell dateCell = row.createCell(0);
            dateCell.setCellValue(lesson.getDate());
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/mm/yyyy"));
            dateCell.setCellStyle(cellStyle);
        }

//        for (int i = 0; i < lessons.size(); i++) {
//            Lesson lesson = lessons.get(i);
//            Row row = sheet.createRow(i);
//            Cell beginTimeCell = row.createCell(3);
//            beginTimeCell.setCellValue(lesson.getBeginTime());
//        }

//        for (int i = 0; i < lessons.size(); i++) {
//            Lesson lesson = lessons.get(i);
//            Row row = sheet.createRow(i);
//            Cell endTimeCell = row.createCell(4);
//            endTimeCell.setCellValue(lesson.getEndTime());
//        }

//        private Row getOrCreateRow(Sheet sheet, int index) {
//            Row row = sheet.getRow(index);
//            if(row == null) {
//                row = sheet.createRow(index);
//            }
//            return row;
//        }

        Row row = sheet.createRow(0);
        Cell hoursDone = row.createCell(7);
        hoursDone.setCellValue("hours done");
        Cell hoursDone2 = row.createCell(8);
        hoursDone2.setCellFormula("SUM(F1:F57)");

        Row row2 = sheet.createRow(1);
        Cell hoursPlanned = row2.createCell(7);
        hoursPlanned.setCellValue("hours planned");
        Cell hoursPlanned2 = row2.createCell(8);
        hoursPlanned2.setCellValue(21);

        Row row3 = sheet.createRow(3);
        Cell lessonsDone = row3.createCell(7);
        lessonsDone.setCellValue("lessons done");
        Cell lessonsDone2 = row3.createCell(8);
//        lessonsDone2.setCellFormula("COUNTIF(B1:B57,done");

        Row row4 = sheet.createRow(4);
        Cell lessonsPlanned = row4.createCell(7);
        lessonsPlanned.setCellValue("lessons planned");
        Cell lessonsPlanned2 = row4.createCell(8);
        lessonsPlanned2.setCellValue(14);
        return workbook;
    }
}
