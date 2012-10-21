package financeiro.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import financeiro.lista.Lista;
import financeiro.lista.ListaRN;

@FacesConverter(forClass = Lista.class)
public class ListaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {

				ListaRN listaRN = new ListaRN();
				return listaRN.carregar(codigo);

			} catch (Exception e) {

				throw new ConverterException(
						"Não foi possível encontrar a o lista de código "
								+ value + "." + e.getMessage());

			}

		}

		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {

			Lista lista = (Lista) value;
			return lista.getCodigo().toString();

		}

		return "";

	}

}