package com.bcm.midia.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bcm.midia.controller.programacao.ProgramacaoController;
import com.bcm.midia.database.entity.Terminal;



@FacesConverter(forClass = Terminal.class, value = "converterListSelection")
public class ConverterList implements Converter {
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value instanceof Terminal) ? String.valueOf(((Terminal) value).getIdTerminal()) : null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null)
			return null;

		ProgramacaoController data = context.getApplication().evaluateExpressionGet(context, "#{programacaoController}", ProgramacaoController.class);

		for (Terminal t: data.getTerminais()) {
			if (t.getIdTerminal() == Integer.parseInt(value))
				return t;
		}

		throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to CompLovDtgrid", value)));
	}
}

/*
 * @FacesConverter("converter") public class ThemeConverter implements Converter
 * {
 * 
 * public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
 * if(value != null && value.trim().length() > 0) { try { Object service =
 * (Object) fc.getExternalContext().getApplicationMap().get("themeService");
 * return service.getThemes().get(Integer.parseInt(value)); }
 * catch(NumberFormatException e) { throw new ConverterException(new
 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
 * "Not a valid theme.")); } } else { return null; } }
 * 
 * public String getAsString(FacesContext fc, UIComponent uic, Object object) {
 * if(object != null) { return String.valueOf(((Theme) object).getId()); } else
 * { return null; } } }
 * 
 * @FacesConverter(forClass = Terminal.class, value = "checkBoxMenuConverter")
 * public class ConverterList implements Converter {
 * 
 * @Inject private TerminalDAO t;
 * 
 * @Override public String getAsString(FacesContext context, UIComponent
 * component, Object value) { if(value != null){ Terminal t = (Terminal) value;
 * return Integer.toString(t.getIdTerminal()); } return null; }
 * 
 * @Override public Object getAsObject(FacesContext context, UIComponent
 * component, String value) { if (value != null && value.trim().length() > 0){
 * 
 * try{
 * 
 * ProgramacaoController data =
 * context.getApplication().evaluateExpressionGet(context,
 * "#{programacaoController}", ProgramacaoController.class); }catch(Exception
 * e){
 * 
 * } throw new ConverterException(new FacesMessage(String.format(
 * "Cannot convert %s to CompLovDtgrid", value)));
 * 
 * Terminal tt = t.findById(Integer.parseInt(value)); return tt; } return null;
 * } }
 * 
 */