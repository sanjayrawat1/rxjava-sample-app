package io.github.sanjayrawat1.reactivex.config.handler;

import io.reactivex.rxjava3.core.Maybe;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * A specialized {@link AsyncHandlerMethodReturnValueHandler} that handles {@link Maybe} return type.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
public class MaybeReturnValueHandler implements AsyncHandlerMethodReturnValueHandler {

    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return returnValue != null && supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Maybe.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }
        final Maybe<?> maybe = Maybe.class.cast(returnValue);
        WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new MaybeDeferredResult<>(maybe), mavContainer);
    }

    private static class MaybeDeferredResult<T> extends DeferredResult<T> {
        public MaybeDeferredResult(Maybe<T> maybe) {
            maybe.subscribe(this::setResult, this::setErrorResult);
        }
    }
}
