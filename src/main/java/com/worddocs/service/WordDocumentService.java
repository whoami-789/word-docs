package com.worddocs.service;

import com.worddocs.model.AzolikFiz;
import com.worddocs.model.Grafik;
import com.worddocs.model.Kredit;
import com.worddocs.repository.AzolikFizRepository;
import com.worddocs.repository.GrafikRepository;
import com.worddocs.repository.KreditRepository;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class WordDocumentService {

    private final KreditRepository kreditRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final GrafikRepository grafikRepository;

    public WordDocumentService(KreditRepository kreditRepository, AzolikFizRepository azolikFizRepository, GrafikRepository grafikRepository) {
        this.kreditRepository = kreditRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.grafikRepository = grafikRepository;
    }

    // Метод для генерации документа
    public byte[] generateWarning(String kreditKod) throws IOException {
        // Получаем данные из базы
        Kredit kredit = kreditRepository.findById(kreditKod)
                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));

        AzolikFiz azolikFiz = kredit.getAzolikFiz();

        // Загрузка шаблона
        InputStream templateStream = new ClassPathResource("templates/LOMBARD_OGOHLANTIRISH.doc").getInputStream();
        XWPFDocument document = new XWPFDocument(templateStream);

        // Проходим по параграфам и заменяем плейсхолдеры
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replacePlaceholder(paragraph, "{{kod}}", kredit.getKod());
            replacePlaceholder(paragraph, "{{summa}}", String.valueOf(kredit.getSumma()));
            replacePlaceholder(paragraph, "{{summa2}}", String.valueOf(kredit.getSumma()));
            replacePlaceholder(paragraph, "{{summa3}}", kredit.getKod());
        }

        // Сохранение сгенерированного документа в байты
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);

        return out.toByteArray();
    }

    public byte[] generateGrafik(String kreditKod) throws IOException {
        // Получаем данные из базы
        Kredit kredit = kreditRepository.findById(kreditKod)
                .orElseThrow(() -> new RuntimeException("Кредит не найден!"));

        AzolikFiz azolikFiz = kredit.getAzolikFiz();

        // Загрузка шаблона
        InputStream templateStream = new ClassPathResource("templates/grafik.doc").getInputStream();
        XWPFDocument document = new XWPFDocument(templateStream);

        // Проходим по параграфам и заменяем плейсхолдеры
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            paragraph.getRuns().clear(); // Очищаем текущие run

            // Проверяем и заменяем плейсхолдеры
            if (text.contains("${fio}")) {
                text = text.replace("${fio}", azolikFiz.getName());
            }
            if (text.contains("${kreditSumma}")) {
                text = text.replace("${kreditSumma}", String.valueOf(kredit.getSumma()));
            }
            if (text.contains("${muddat}")) {
                text = text.replace("${muddat}", String.valueOf(kredit.getSrokkred()));
            }
            if (text.contains("${oylikFoiz}")) {
                text = text.replace("${oylikFoiz}", String.valueOf(kredit.getProsent()));
            }

            // Добавляем обновленный текст
            paragraph.createRun().setText(text);
        }

        // Заполнение таблицы
        XWPFTable table = document.getTableArray(0); // Предполагаем, что таблица первая

        // Добавление данных в таблицу
        if (table != null) {
            // Предположим, что у вас есть метод для получения всех Grafik, связанных с кредитом
            List<Grafik> grafikList = grafikRepository.findAllByNumdog(kredit.getNumdog());

            // Удаляем первую пустую строку, если она есть
            while (table.getNumberOfRows() > 1) {
                table.removeRow(1); // Удаляем строки, начиная со второй (первая - заголовок)
            }
            int rowIndex = 1; // Начинаем с 1, если строка заголовка - это 0

            // Добавление данных в таблицу
            for (Grafik grafik : grafikList) {
                XWPFTableRow newRow = table.createRow();
                // Установка номера в первую ячейку
                newRow.getCell(0).setText(String.valueOf(rowIndex++)); // Увеличиваем номер после использования
                // Установка остальных данных
                newRow.getCell(1).setText(String.valueOf(grafik.getDats()));
                newRow.getCell(2).setText(String.valueOf(grafik.getPogKred()));
                newRow.getCell(3).setText(String.valueOf(grafik.getPogProc()));
                newRow.getCell(4).setText(String.valueOf(grafik.getOstatok()));
                newRow.getCell(5).setText(String.valueOf(grafik.getPogKred().intValue() + grafik.getPogProc().intValue()));
                // Добавьте дополнительные столбцы по необходимости
            }
        }

        // Сохранение сгенерированного документа в байты
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);

        return out.toByteArray();
    }




    // Метод для замены плейсхолдера в параграфе
    private void replacePlaceholder(XWPFParagraph paragraph, String placeholder, String value) {
        if (paragraph.getText().contains(placeholder)) {
            String updatedText = paragraph.getText().replace(placeholder, value);
            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
            paragraph.createRun().setText(updatedText, 0);
        }
    }
}

