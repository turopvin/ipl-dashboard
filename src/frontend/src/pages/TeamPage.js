import {React, useEffect} from "react";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {MatchSmallCard} from "../components/MatchSmallCard";

export const TeamPage = () => {

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch('http://localhost:8181/team/Delhi%20Capital')
                const data = await response.json()
                console.log(data)
            }
            fetchMatches()
        },
        []
    )
    return (
        <div className="TeamPage">
            <h1>Team name</h1>
            <MatchDetailCard/>
            <MatchSmallCard/>
            <MatchSmallCard/>
            <MatchSmallCard/>
        </div>
    );
}