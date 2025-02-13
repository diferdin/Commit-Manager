package com.diferdin.commitmanager

import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vcs.CheckinProjectPanel
import com.intellij.openapi.vcs.changes.CommitContext
import com.intellij.openapi.vcs.checkin.CheckinHandler
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory

class CommitMessageCheckHandlerFactory:CheckinHandlerFactory() {
    override fun createHandler(panel: CheckinProjectPanel, commitContext: CommitContext): CheckinHandler {
        return CommitMessageCheckHandler(panel);
    }
}

    class CommitMessageCheckHandler(private val panel: CheckinProjectPanel) : CheckinHandler() {

        override fun beforeCheckin(): ReturnResult {
            val commitMessage = panel.commitMessage

            val validationResult = validateCommitMessage(commitMessage)
            if (!validationResult.isValid) {
                // Show validation error message
                Messages.showErrorDialog(
                    validationResult.errorMessage,
                    "Invalid Commit Message"
                )
                return ReturnResult.CANCEL
            }
            return ReturnResult.COMMIT
        }

        private fun validateCommitMessage(commitMessage: String): ValidationResult {
            // Example rule: Commit message must start with "[ISSUE-123]"
            val regex = "^EACBM-\\d{5} [A-Z][A-Za-z0-9\\s,();:]{0,99}\\.\$".toRegex()
            return if (!commitMessage.matches(regex)) {
                ValidationResult(
                    isValid = false,
                    errorMessage = "The commit message is incorrect. It should:\n" +
                            "- start with EACBM-12345\n" +
                            "- one space\n" +
                            "- a description that starts with a capital letter [max 100 chars]\n" +
                            "- a final dot.\n" +
                            "Example: EACBM-12345 Refactored myClass."
                )
            } else {
                ValidationResult(isValid = true, errorMessage = "")
            }
        }
    }

    data class ValidationResult(val isValid: Boolean, val errorMessage: String)
