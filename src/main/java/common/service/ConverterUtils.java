package common.service;

import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ConverterUtils {

    public static WinApiClass convert(model.TWinApiClass topic) {
        List<WinApiFunction> collect = topic.getFunctions().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
        return new WinApiClass(
                topic.id,
                topic.name,
                topic.description,
                topic.example,
                collect);
    }

    public static TWinApiClass convert(WinApiClass topic) {
        List<TWinApiFunction> collect = topic.getFunctions().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
        return new TWinApiClass(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getExample(),
                collect);
    }

    public static WinApiFunction convert(TWinApiFunction function) {
        List<WinApiParameter> collect = function.getParams().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
        return new WinApiFunction(
                function.getId(),
                function.getName(),
                function.getDescription(),
                function.getExample(),
                collect);
    }

    private static WinApiParameter convert(TWinApiParams param) {
        return new WinApiParameter(
                param.getId(),
                param.getType(),
                param.getName());
    }

    public static TWinApiFunction convert(WinApiFunction function) {
        List<TWinApiParams> collect = function.getParams().stream()
                .map(ConverterUtils::convert)
                .collect(Collectors.toList());
        return new TWinApiFunction(
                function.getId(),
                function.getName(),
                function.getDescription(),
                function.getExample(),
                collect);
    }

    private static TWinApiParams convert(WinApiParameter param) {
        return new TWinApiParams(
                param.getId(),
                param.getName(),
                param.getType());
    }
}
