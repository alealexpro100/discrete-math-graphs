package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class Example extends goToButtons {

    @FXML
    private Pane pane1;

    GraphDraw gd;

    @FXML
    void initialize() {
        //Автозапуск после открытия окна.
        System.out.println("test");
        //Создание объекта рендера графа.
        //Первый параметр - панель вывода, второй параметр - лимит (максимальное число вершин)
        //ВАЖНО: Пока что нет проверки на выход за пределы лимита
        gd=new GraphDraw(pane1, 10);
    }

    @FXML
    private void onClickTestAction() throws Exception {
        //Очистка рендера.
        gd.ClearRender();
        //Полная очистка объекта от данных.
        gd.Clear();
        //Добавление вершины. Будет возвращаться её индекс (id).
        gd.AddPoint(2); //0
        gd.AddPoint(4); //1
        gd.AddPoint(5); //2
        gd.AddPoint(5); //3
        gd.AddPoint(7); //4
        try {
            gd.AddPoint(8);
        } catch (Exception e) {
            System.out.println("Normal. Ignoring.");
        }
        //Изменение вершины
        gd.SetPoint(2, 1);
        try {
            gd.SetPoint(8, 8);
        } catch (Exception e) {
            System.out.println("Normal. Ignoring.");
        }
        //Удаление вершины по индексу.
        gd.DelPoint(2);
        gd.DelPoint(3);
        try {
            gd.DelPoint(8);
        } catch (Exception e) {
            System.out.println("Normal. Ignoring.");
        }
        //Получение точки по индексу.
        System.out.println(gd.GetPoint(0)); //2
        System.out.println(gd.GetPoint(1)); //4
        System.out.println(gd.GetPoint(2)); //5
        //Добавление связи между двумя точками (1-ая опция - 1-ая вершина, 2-ая опция - 2-ая вершина, размер ребра).
        //SetLink и AddLink - одно и то же.
        gd.SetLink(0, 1, 2);
        gd.SetLink(0, 2, 2);
        gd.SetLink(1, 0, 4);
        gd.SetLink(2, 0, 4);
        try {
            gd.SetLink(4, 4, 1);
        } catch (Exception e) {
            System.out.println("Normal. Ignoring.");
        }
        gd.Clear();
        for (int i=0; i<10; i++) {
            gd.AddPoint(i);
        }
        gd.SetLink(0, 1, 1);
        gd.SetLink(1, 2, 2);
        gd.SetLink(1, 3, 2);
        gd.SetLink(2, 4, 2);
        gd.SetLink(2, 5, 2);
        //Вывод данных объекта в консоль (таблица смежности и значения точек).
        gd.TextOut();
        //Рендер. Пока не работает.
        //Простой рендер. Работает для любого графа.
        //gd.RenderStupid();
        //Рендер дерева.
        gd.RenderTree(0);
        //MouseGestures mg = new MouseGestures();
        //gd.GetNodeCircle(2).setFill(Color.AZURE);
        //mg.makeDraggle(gd.GetNodeCircle(2));
    }

    public static class MouseGestures {

        double orgSceneX, orgSceneY;
        double orgTranslateX, orgTranslateY;
    
        public void makeDraggle(Node node) {
            node.setOnMousePressed(OnMousePressedEventHandler);
            node.setOnMouseDragged(OnMouseDraggedEventHandler);
        }
    
        EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {
    
            @Override
            public void handle(MouseEvent t) {

                if (t.getSource() instanceof Circle) {
                    System.out.println("Pressing circle.");
                }
    
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
    
                Node p = ((Node) (t.getSource()));
    
                orgTranslateX = p.getTranslateX();
                orgTranslateY = p.getTranslateY();
            }
        };
    
        EventHandler<MouseEvent> OnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
    
            @Override
            public void handle(MouseEvent t) {

                if (t.getSource() instanceof Circle) {
                    System.out.println("Moving circle.");
                }
    
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
    
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
    
                Node p = ((Node) (t.getSource()));
    
                p.setTranslateX(newTranslateX);
                p.setTranslateY(newTranslateY);
    
            }
        };
    }
}
