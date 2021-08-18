package com.sample.web.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleResponseData<T> extends ResponseData {
    List<T> items;
}
