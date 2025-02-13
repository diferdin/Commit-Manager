package com.diferdin.commitmanager

object CommitMessageValidator {
    fun validateCommitMessage(commitMessage: String, regex: String): ValidationResult {
        return if (!commitMessage.matches(regex.toRegex())) {
            ValidationResult(
                isValid = false,
                errorMessage = "Commit message does not match the required format: $regex"
            )
        } else {
            ValidationResult(isValid = true, errorMessage = "")
        }
    }
}
