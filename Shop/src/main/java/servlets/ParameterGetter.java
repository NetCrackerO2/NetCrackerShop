package servlets;


import javax.faces.convert.ConverterException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.Function;

public class ParameterGetter {
    public static String getStringParameter(HttpServletRequest request, String parameterName) {
        return Optional.ofNullable(
                request.getParameter(parameterName)
        ).orElseThrow(() -> new NullPointerException(parameterName));
    }

    public static <T> T getConvertedParameter(HttpServletRequest request,
                                              String parameterName,
                                              Function<String, T> converter) {
        String value = getStringParameter(request, parameterName);
        try {
            return converter.apply(value);
        } catch (Exception e) {
            throw new ConverterException(parameterName);
        }
    }
}
