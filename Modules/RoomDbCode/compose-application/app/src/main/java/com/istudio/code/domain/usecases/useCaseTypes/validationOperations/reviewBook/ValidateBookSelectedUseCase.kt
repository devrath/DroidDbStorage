package com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook

import javax.inject.Inject

class ValidateBookSelectedUseCase @Inject constructor() {

    operator fun invoke(data : String) : Result<Boolean>{
        return try{
            if(data.isNotEmpty()){
                // Success
                Result.success(true)
            }else{
                // Failure
                Result.success(false)
            }
        }catch (ex:Exception){
            // Exception
            Result.failure(ex)
        }
    }

}