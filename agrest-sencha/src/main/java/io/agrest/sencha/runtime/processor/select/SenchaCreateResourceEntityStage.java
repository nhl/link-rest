package io.agrest.sencha.runtime.processor.select;

import io.agrest.ResourceEntity;
import io.agrest.base.protocol.Exp;
import io.agrest.meta.AgDataMap;
import io.agrest.meta.AgEntity;
import io.agrest.runtime.entity.*;
import io.agrest.runtime.processor.select.CreateResourceEntityStage;
import io.agrest.runtime.processor.select.SelectContext;
import io.agrest.sencha.SenchaRequest;
import io.agrest.sencha.runtime.entity.ISenchaFilterExpressionCompiler;
import org.apache.cayenne.di.Inject;

import java.util.List;

public class SenchaCreateResourceEntityStage extends CreateResourceEntityStage {


    private ISenchaFilterExpressionCompiler senchaFilterProcessor;

    public SenchaCreateResourceEntityStage(
            @Inject AgDataMap dataMap,
            @Inject IExpMerger expConstructor,
            @Inject ISortMerger sortConstructor,
            @Inject IMapByMerger mapByConstructor,
            @Inject ISizeMerger sizeConstructor,
            @Inject IIncludeMerger includeConstructor,
            @Inject IExcludeMerger excludeConstructor,
            @Inject ISenchaFilterExpressionCompiler senchaFilterProcessor) {

        super(dataMap, expConstructor, sortConstructor, mapByConstructor,
                sizeConstructor, includeConstructor, excludeConstructor);

        this.senchaFilterProcessor = senchaFilterProcessor;
    }

    @Override
    protected <T> void doExecute(SelectContext<T> context) {
        super.doExecute(context);

        ResourceEntity<T> entity = context.getEntity();
        parseFilter(entity.getAgEntity(), context).forEach(entity::andQualifier);
    }

    protected <T> List<Exp> parseFilter(AgEntity<?> entity, SelectContext<T> context) {
        SenchaRequest senchaRequest = SenchaRequest.get(context);
        return senchaFilterProcessor.process(entity, senchaRequest.getFilters());
    }
}
