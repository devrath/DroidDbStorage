package com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook

import javax.inject.Inject

class ValidateRatingSelectionUseCase @Inject constructor() {
    operator fun invoke(data : String) : Result<Boolean>{
        try{
            if(data.isNullOrEmpty()){
                // Failure
                return Result.success(false)
            }else{
                // Success
                return Result.success(true)
            }
        }catch (ex:Exception){
            // Exception
            return Result.failure(ex)
        }
    }

}