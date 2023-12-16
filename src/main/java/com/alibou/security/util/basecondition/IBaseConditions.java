package com.alibou.security.util.basecondition;

public interface IBaseConditions<T> extends Filterable, Orderable,
        SecurityFilterable, CustomPageable<T> {
}
