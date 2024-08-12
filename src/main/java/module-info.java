module com.survey.surveysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    //requires de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

    opens com.survey.surveysystem to javafx.fxml;
    opens com.survey.surveysystem.models to javafx.base;

    exports com.survey.surveysystem;
    //requires org.apache.logging.log4j;
}
