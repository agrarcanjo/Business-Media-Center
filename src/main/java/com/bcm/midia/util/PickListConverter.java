package com.bcm.midia.util;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "primeFacesPickListConverter")
public class PickListConverter implements Converter {

	/*
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object ret = null;
		if (arg1 instanceof PickList) {
			Object dualList = ((PickList) arg1).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Iterator iterator = dl.getSource().iterator(); iterator.hasNext();) {
				Object o = iterator.next();
				String id = (new StringBuilder()).append(((Midia) o).getId()).toString();
				if (arg2.equals(id)) {
					ret = o;
					break;
				}
			}

			if (ret == null) {
				for (Iterator iterator1 = dl.getTarget().iterator(); iterator1.hasNext();) {
					Object o = iterator1.next();
					String id = (new StringBuilder()).append(((AbstractBean) o).getId()).toString();
					if (arg2.equals(id)) {
						ret = o;
						break;
					}
				}

			}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";
		if (arg2 instanceof AbstractBean)
			str = ((AbstractBean) arg2).getId().toString();
		return str;
	}
//
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		PlaylistDAO playlistDAO = new PlaylistDAO();
//		Playlist jogador = null;
//		if ((value != null) && (!value.equals(""))) {
//			jogador = playlistDAO.findById(Long.valueOf(value));
//		}
//		return jogador;
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		Integer retorno = null;
//		if (!(value == null)) {
//			Playlist jogador = new Playlist();
//			jogador = (Playlist) value;
//			retorno = jogador.getIdPlaylist();
//		}
//		return retorno.toString();
//	}
	
	*/
	
	private static Map<Object, String> entities = new WeakHashMap<Object, String>();

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        for (Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }
}