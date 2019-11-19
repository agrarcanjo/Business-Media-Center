package com.bcm.midia.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bcm.midia.controller.programacao.ProgramacaoController;
import com.bcm.midia.database.entity.Playlist;



@FacesConverter(forClass = Playlist.class, value = "converterListPlaylist")
public class ConverterListPlaylist implements Converter {
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value instanceof Playlist) ? String.valueOf(((Playlist) value).getIdPlaylist()) : null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null)
			return null;

		ProgramacaoController data = context.getApplication().evaluateExpressionGet(context, "#{programacaoController}", ProgramacaoController.class);

		for (Playlist t: data.getPlaylist()) {
			if (t.getIdPlaylist() == Integer.parseInt(value))
				return t;
		}

		throw new ConverterException(new FacesMessage(String.format("Não foi possível converter  %s", value)));
	}
}
