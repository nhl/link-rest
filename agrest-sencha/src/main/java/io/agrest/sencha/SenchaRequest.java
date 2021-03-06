package io.agrest.sencha;

import io.agrest.AgRequest;
import io.agrest.runtime.processor.select.SelectContext;
import io.agrest.sencha.protocol.Filter;

import java.util.Collections;
import java.util.List;

/**
 * Sencha extensions of the standard {@link AgRequest}.
 *
 * @since 2.13
 */
public class SenchaRequest {

    private static final String ATTRIBUTE_KEY = SenchaRequest.class.getName();
    private List<Filter> filters;

    protected SenchaRequest() {
    }

    public static SenchaRequest get(SelectContext<?> context) {
        return (SenchaRequest) context.getAttribute(ATTRIBUTE_KEY);
    }

    public static void set(SelectContext<?> context, SenchaRequest request) {
        context.setAttribute(ATTRIBUTE_KEY, request);
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Filter> getFilters() {
        return filters != null ? filters : Collections.emptyList();
    }

    public static class Builder {
        private SenchaRequest request;

        public Builder() {
            this.request = new SenchaRequest();
        }

        public SenchaRequest build() {
            return request;
        }

        public Builder filters(List<Filter> filters) {
            this.request.filters = filters;
            return this;
        }
    }
}
