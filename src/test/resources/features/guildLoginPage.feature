@guildLogin
Feature: This feature is about testing automation guild login page functionality

  Scenario: GuildAutomation login page loads when logged in to AutomationGuild website
    When AutomationGuild Website "https://guildconferences.com/my-account/" is opened
    Then AutomationGuild login page should be displayed by default

#  Scenario Outline: GuildAutomation Homepage should open on successful login scenario
#    Given AutomationGuild Website "https://guildconferences.com/my-account/" is opened
#    When user logged in with below "<username>" and "<password>"
#    Then AutomationGuild Home Page should be displayed
#    Examples:
#      | username      | password  |
#      | onkar.mundaye | rpgrpg@18 |
#      | onkar.mundaye | rpgrpg@1  |