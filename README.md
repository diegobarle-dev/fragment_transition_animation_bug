# fragment_transition_animation_bug
This project shows a bug in the latest Fragment library version (1.3.6) with regards to shared elements transition breaking animations when no element is been shared
Find the report here: https://issuetracker.google.com/200710672

Update: the bug was resolved by Google and should be availabe in following releases after **1.4.0-alpha10**.
In the meantime, as a workaround the suggestion is to wrap any extra animations (except the one from enter/exit/shared transitions) inside the `doOnLayout` method upon `onResume` together with setting a default `enterTransition` animation in the SecondFragment.

    override fun onResume() {
        super.onResume()
        binding.viewBackground.doOnLayout {
            startCircularRevealAnimation()
        }
    }

    enterTransition = TransitionSet()
