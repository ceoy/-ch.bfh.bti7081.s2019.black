package ch.bfh.bti7081.s2019.black.spitexorganizer.report.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "report", layout = MainLayout.class)
@PageTitle("Verlaufsberichte")
public class ReportView extends VerticalLayout implements HasUrlParameter<Long> {

    @Autowired
    ReportApi reportApi;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        //long is the patient id
        List<ReportDto> reports = reportApi.findByPatientId(aLong);
        Grid<ReportDto> grid = new Grid<>();
        grid.setItems(reports);
        grid.addColumn(ReportDto::getId).setHeader("Id");
        grid.addColumn(ReportDto::getDescription).setHeader("Beschreibung");
        grid.addColumn(reportDto -> (reportDto.getEdit()) ? "Nicht Abgeschlossen" : "Abgeschlossen").setHeader("Status");

        grid.addItemClickListener(reportDtoItemClickEvent -> UI.getCurrent().navigate(ReportEditView.class, reportDtoItemClickEvent.getItem().getAppointmentDto().getId()));
        H1 title = new H1("Alle Verlaufsberichte");
        this.add(title);
        this.add(grid);
    }
}
