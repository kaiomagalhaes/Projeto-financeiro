package financeiro.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


import financeiro.grupo.produtos.Grupo;
import financeiro.grupo.produtos.GrupoRN;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {

				GrupoRN grupoRN = new GrupoRN();
				return grupoRN.carregar(codigo);

			} catch (Exception e) {

				throw new ConverterException(
						"Não foi possível encontrar a o grupo de código "
								+ value + "." + e.getMessage());

			}

		}

		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {

			Grupo grupo = (Grupo) value;
			return grupo.getCodigo().toString();

		}

		return "";

	}

}