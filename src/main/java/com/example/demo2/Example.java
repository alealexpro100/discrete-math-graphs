package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public class Example extends goToButtons {

    @FXML
    private Pane pane1;

    GraphDraw<Integer> gd;

    @FXML
    void initialize() {
        //Автозапуск после открытия окна.
        System.out.println("test");
        //Создание объекта рендера графа.
        //Первый параметр - панель вывода, второй параметр - лимит (максимальное число вершин)
        //ВАЖНО: Пока что нет проверки на выход за пределы лимита
        gd=new GraphDraw<Integer>(pane1, 5);
    }

    @FXML
    private void onClickTestAction() throws Exception {
        //Добавление вершины. Будет возвращаться её индекс (id).
        gd.AddPoint(2); //0
        gd.AddPoint(4); //1
        gd.AddPoint(5); //2
        gd.AddPoint(5); //3
        //Изменение вершины
        gd.SetPoint(2, 1);
        //Удаление вершины по индексу.
        gd.DelPoint(2);
        //Получение точки по индексу.
        System.out.println(gd.GetPoint(0)); //2
        System.out.println(gd.GetPoint(1)); //4
        System.out.println(gd.GetPoint(2)); //5
        //Добавление связи между двумя точками (1-ая опция - 1-ая вершина, 2-ая опция - 2-ая вершина, размер ребра).
        //SetLink и AddLink - одно и то же.
        gd.AddLink(0, 1, 2);
        gd.AddLink(1, 0, 2);
        gd.SetLink(0, 1, 2);
        gd.AddLink(1, 0, 2);
        //Вывод данных объекта в консоль (таблица смежности и значения точек).
        gd.TextOut();
        //Полная очистка объекта от данных.
        gd.Clear();
        //Рендер. Пока не работает.
        gd.render();
        //Очистка рендера.
        gd.ClearRender();
        //Проверка работоспособности графики.
        gd.test_gr();
    }
}
