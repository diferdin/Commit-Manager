package com.diferdin.commitmanager

import com.diferdin.commitmanager.commitrules.CommitRulesSettings
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.PropertyBinding
import com.intellij.ui.layout.panel

class CommitRulesConfigurable : Configurable {

    private var customRegex: String = CommitRulesSettings.getInstance().commitMessageRegex

    override fun createComponent(): DialogPanel {
        return panel {
            row("Commit Message Regex:") {
                textField(
                    PropertyBinding(
                    get = { customRegex },
                    set = { customRegex = it }
                )).comment("Define a regex for validating commit messages.")
            }
        }
    }


    override fun isModified(): Boolean {
        return customRegex != CommitRulesSettings.getInstance().commitMessageRegex
    }

    override fun apply() {
        CommitRulesSettings.getInstance().commitMessageRegex = customRegex
    }

    override fun getDisplayName(): String = "Commit Message Rules"
}
