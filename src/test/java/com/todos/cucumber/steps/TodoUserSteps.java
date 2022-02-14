package com.todos.cucumber.steps;


import com.google.common.base.Splitter;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.events.AfterExample;
import net.serenitybdd.core.annotations.events.AfterScenario;
import net.serenitybdd.core.annotations.events.BeforeExample;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import com.todos.cucumber.MissingTodoItemsException;
import com.todos.screenplay.model.TodoStatusFilter;
import com.todos.screenplay.questions.TheItems;
import com.todos.screenplay.tasks.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.model.TestOutcome;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class TodoUserSteps {

    @Before
    public void set_the_stage() {
        setTheStage(new OnlineCast());
    }

    @Before
    public void cucumberBeforeScenario() {
        System.out.println("CUCUMBER BEFORE");
    }
    //
    // These methods illustrate the Cucumber lifecycle methods available in Serenity
    //
    @BeforeScenario
    public void beforeScenario(TestOutcome outcome) {
        System.out.println("BEFORE SCENARIO " + outcome.getName());
    }

    @BeforeScenario
    public void beforeAScenario() {
        System.out.println("BEFORE SCENARIO");
    }

    @BeforeExample
    public void beforeExample() {
        System.out.println("BEFORE EXAMPLE");
    }

    @AfterExample
    public void deleteAllTheTasks() {
        System.out.println("AFTER EXAMPLE");
        theActorInTheSpotlight().attemptsTo(
                DeleteAllTheItems.onThePage()
        );
    }

    @AfterScenario
    public void afterScenario(TestOutcome outcome) {
        System.out.println("AFTER SCENARIO " + outcome.getName());
    }

    @ParameterType(".*")
    public Actor actor(String actor) {
        return OnStage.theActorCalled(actor);
    }

    @ParameterType("All|Active|Completed")
    public TodoStatusFilter filter(String filter) {
        return TodoStatusFilter.valueOf(filter);
    }

    @ParameterType(".*")
    public List<String> items(String listOfItems) {
        return Splitter.on(",").trimResults().omitEmptyStrings().splitToList(listOfItems);
    }

    @Given("that {actor} has an empty todo list")
    public void that_userOne_has_an_empty_todo_list(Actor actor) {
        actor.wasAbleTo(Start.withAnEmptyTodoList());
    }

    @Given("that {actor} has a todo list containing {items}")
    public void that_userOne_has_an_empty_todo_list(Actor actor, List<String> items) {
        actor.wasAbleTo(Start.withATodoListContaining(items));
    }

    @When("{actor} adds {string} to his/her list")
    public void adds_to_his_list(Actor actor, String item) {
        actor.attemptsTo(AddATodoItem.called(item));
    }

    @When("{actor} deletes the task called {string}")
    public void deletes_an_item(Actor actor, String item) {
        actor.attemptsTo(DeleteAnItem.called(item));
    }

    @Then("{string} should be recorded in his/her list")
    public void item_should_be_recorded_in_the_list(String expectedItem) {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), hasItem(expectedItem))
                .orComplainWith(MissingTodoItemsException.class, "Missing todo " + expectedItem));
    }

    @Then("his/her todo list should contain {items}")
    public void todo_list_should_contain(List<String> expectedItems) {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                .orComplainWith(MissingTodoItemsException.class, "Missing todos " + expectedItems));
    }

    @Then("{actor}'s todo list should contain {items}")
    public void a_users_todo_list_should_contain(Actor actor, List<String> expectedItems) {
        actor.should(seeThat(TheItems.displayed(), equalTo(expectedItems))
                .orComplainWith(MissingTodoItemsException.class, "Missing todos " + expectedItems));
    }


    @Then("his/her todo list should be empty")
    public void todo_list_should_be_empty() {
        theActorInTheSpotlight().should(seeThat(TheItems.displayed(), equalTo(EMPTY_LIST)));
    }

    @Then("^s?he (?:completes|has completed) the task called '(.*)'$")
    public void completes_task_called(String item) {
        theActorInTheSpotlight().attemptsTo(
                CompleteItem.called(item)
        );
    }

    @When("{actor} filters her list to show only {filter} tasks")
    public void filters_tasks_by(Actor actor, TodoStatusFilter status) {
        actor.attemptsTo(
                FilterItems.toShow(status)
        );
    }
}
