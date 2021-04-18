package com.hernandazevedo.zaap.search.domain.failure

import com.hernandazevedo.zaap.core.base.exception.Failure

data class SearchFailure(val message: String) : Failure.FeatureFailure()
