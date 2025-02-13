package com.diferdin.commitmanager.commitrules

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "CommitRulesSettings", storages = [Storage("CommitRulesSettings.xml")])
@Service
class CommitRulesSettings : PersistentStateComponent<CommitRulesSettings.State> {

    private var state = State()

    class State {
        var commitMessageRegex: String = "\\[\\w+-\\d+\\] .*"
    }

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    var commitMessageRegex: String
        get() = state.commitMessageRegex
        set(value) {
            state.commitMessageRegex = value
        }

    companion object {
        fun getInstance(): CommitRulesSettings {
            return com.intellij.openapi.application.ApplicationManager.getApplication()
                .getService(CommitRulesSettings::class.java)
        }
    }
}
