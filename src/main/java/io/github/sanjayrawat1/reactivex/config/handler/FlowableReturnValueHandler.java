package io.github.sanjayrawat1.reactivex.config.handler;

import io.reactivex.rxjava3.core.Flowable;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * A specialized {@link AsyncHandlerMethodReturnValueHandler} that handles {@link Flowable} return type.
 *
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
public class FlowableReturnValueHandler implements AsyncHandlerMethodReturnValueHandler {

    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return returnValue != null && supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Flowable.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }
        final Flowable<?> flowable = Flowable.class.cast(returnValue);
        WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new FlowableDeferredResult<>(flowable), mavContainer);
    }

    private static class FlowableDeferredResult<T> extends DeferredResult<T> {
        public FlowableDeferredResult(Flowable<T> flowable) {
            flowable.subscribe(this::setResult, this::setErrorResult);
        }
    }
}
