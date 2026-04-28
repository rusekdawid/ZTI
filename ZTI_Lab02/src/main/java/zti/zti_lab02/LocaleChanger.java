package zti.zti_lab02;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Locale;

@Named("localeChanger")
@SessionScoped
public class LocaleChanger implements Serializable {

    private Locale locale;

    @PostConstruct
    public void init() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (ctx != null) {
            locale = ctx.getExternalContext().getRequestLocale();
        } else {
            locale = new Locale("pl");
        }
    }

    public Locale getLocale() { return locale; }

    public String getLanguage() { return locale.getLanguage(); }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
